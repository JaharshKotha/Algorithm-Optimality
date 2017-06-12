import fetch from 'node-fetch';
import { Response } from 'node-fetch';
import * as Config from 'config';


import { values } from '../util/utils';
import { ActivityLogs } from './models/ActivityLog';
import { Configuration, NewConfiguration } from './models/Configuration';
import { Devices, EntranceChange, DeviceNameChange } from './models/Device';
import { DeviceStatistics } from './models/DeviceStatistic';
import { Entrances, NewEntrances, EntranceNameChange } from './models/Entrance';
import { Events } from './models/Event';
import { Venues } from './models/Venue';

const config: any = Config.get('chiron');
const CHIRON_URL: string = `${config.host}`;

const requestOptions: any = {
    method: 'GET',
    headers: {
        'x-api-key': config.apiKey
    }
};

function sendRequest(url: string): Promise<any> {
    return fetch(url, requestOptions)
        .then(toJSON);
}

function sendPost(url: string, body: any): Promise<any> {
    let postRequestOptions: any = requestOptions;
    postRequestOptions.method = 'POST';
    postRequestOptions.body = JSON.stringify(body);

    return fetch(url, postRequestOptions)
        .then(toJSON);
}

function toJSON(res: Response): Promise<any> {

    return res.json().then((json: any) => { return json.message; });

}




export class ChironClient {
    public static fetchActivityLogs(request: { venueId: string, eventId?: string }): Promise<ActivityLogs> {
        return sendRequest(`${CHIRON_URL}/activity?venue=${request.venueId}&event=${request.eventId}`);
    }

    public static fetchConfiguration(request: { eventId: string }): Promise<Configuration> {
        return sendRequest(`${CHIRON_URL}/configuration?eventId=${request.eventId}`);
    }

    public static fetchDevices(request: { venueId: string }): Promise<Devices> {
        return sendRequest(`${CHIRON_URL}/devices?venue=${request.venueId}`);
    }

    public static fetchDeviceStatistics(request: { venueId: string }): Promise<DeviceStatistics> {
        return sendRequest(`${CHIRON_URL}/device/stat?venue=${request.venueId}`);
    }

    public static fetchEntrances(request: { venueId: string }): Promise<Entrances> {
        return sendRequest(`${CHIRON_URL}/venue/gates?venue=${request.venueId}`);
    }

    public static fetchEvents(request: { venueId: string }): Promise<Events> {
        return sendRequest(`${CHIRON_URL}/venue/events?venue=${request.venueId}`)
            .then(values);
    }



    public static tem(data: any): Promise<any> {

        var venue_count: number = data.totalVenueCount;
        var item;
        var promises = [];
        for (var i = 0; i < venue_count; i++) {
            var tem: any = data.Venues[i];
            tem.totalAttended = 0;


            for (var j = 0; j < tem.events.length; j++) {
                var tem_event_id: string = tem.events[j];
                tem_event_id = "3F00529A4638866A";
                var clearsky_url = 'http://presence.clearsky.nonprod-tmaws.io/event/' + tem_event_id;
                promises.push(fetch(clearsky_url).then(
                    function (response) {
                        if (response.status !== 200) {
                            console.log('Looks like there was a problem. Status Code: ' +
                                response.status);
                            return;
                        }
                         return response.json().then(function (data) {
                            tem.totalAttended += parseInt(data.totalAttended);

                            // console.log(tem);
                        })
                            .catch(function (err) {
                                console.log('Fetching Error :-S', err);
                            });


                    }
                )
                    .catch(function (err) {
                        console.log('Fetch Error :-S', err);
                    })
                )



            }


        }

        return Promise.all(promises).then(function () {
            console.log("I'm in ");
            console.log(data.Venues[0].events);
            return data;
        })
    }

    public static daySummary(request: { whatstoday: string }): Promise<any> {
        let r;
        r = sendRequest(`http://localhost:8082/daySummary?day=2017-05-03`).then((data) => {

            var pd = ChironClient.tem(data);
            //console.log(data);
            return pd;

        });

        return r;

    }

    public static fetchVenues(request: { domainId: string }): Promise<Venues> {
        return sendRequest(`${CHIRON_URL}/venues?domainId=${request.domainId}`);
    }

    public static entranceChange(venueId: string, body: EntranceChange): Promise<any> {
        return sendPost(`${CHIRON_URL}/device/gate?venue=${venueId}`, body);
    }


    public static newConfiguration(body: NewConfiguration): Promise<any> {
        return sendPost(`${CHIRON_URL}/configuration`, body);
    }

    public static newEntrances(venueId: string, body: NewEntrances): Promise<any> {
        return sendPost(`${CHIRON_URL}/venue/gates?venue=${venueId}`, body);
    }

    public static renameDevice(venueId: string, body: DeviceNameChange): Promise<any> {
        return sendPost(`${CHIRON_URL}/device/name?venue=${venueId}`, body);
    }

    public static renameEntrance(venueId: string, body: EntranceNameChange): Promise<any> {
        return sendPost(`${CHIRON_URL}/venue/gates/update?venue=${venueId}`, body);
    }
}
