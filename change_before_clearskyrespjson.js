import fetch from 'node-fetch';

export function fetchSummary(data: any): Promise<any> {

    var venue_count: number = data.totalVenueCount;
    var promises = [];
    for (var i = 0; i < venue_count; i++) {
        data.Venues[i].totalAttended = 0;
        data.Venues[i].Devices;
        if (data.Venues[i].events == null) {
            continue;
        }

        for (let j = 0; j < data.Venues[i].events.length; j++) {
            var tem_event_id: string = data.Venues[i].events[j];
            var myhash: any = {};

            var clearsky_url = 'http://presence.clearsky.preprod.prod-tmaws.io/event/' + tem_event_id;
            console.log(clearsky_url);
            promises.push(fetch(clearsky_url).then(
                function (clearsky_response) {
                    if (clearsky_response.status !== 200) {
                        console.log('Looks like there was a problem. Status Code: ' + clearsky_response.status);
                        return;
                    }
                    return clearsky_response.json().then(function (clearsky_response_json) {
                        console.log(clearsky_response_json);

                       
                            data.Venues[i].totalAttended += parseInt(clearsky_response_json.totalAttended);
                        
                        console.log("I was in");
                        if (clearsky_response_json.devices == null) {
                            return;
                        }

                        if (data.Venues[i].Devices != null) {
                            for (var device_cnt = 0; device_cnt < clearsky_response_json.devices.length; device_cnt++) {
                                if (clearsky_response_json.devices[device_cnt].horizonId in myhash) {
                                    var scan_cnt = myhash[clearsky_response_json.devices[device_cnt].horizonId];
                                    scan_cnt += clearsky_response_json.devices[device_cnt].total;
                                    myhash[clearsky_response_json.devices[device_cnt].horizonId] = scan_cnt;
                                    data.devices[device_cnt].total = scan_cnt;
                                    data.Venues[i].Devices.push(clearsky_response_json.devices[device_cnt]);
                                }
                                else {
                                    myhash[clearsky_response_json.devices[device_cnt].horizonId] = clearsky_response_json.devices[device_cnt].total;
                                    data.Venues[i].Devices.push(clearsky_response_json.devices[device_cnt]);
                                }
                            }


                        }
                        else {

                            for (var device_cnt = 0; device_cnt < clearsky_response_json.devices.length; device_cnt++) {

                                myhash[clearsky_response_json.devices[device_cnt].horizonId] = clearsky_response_json.devices[device_cnt].total;

                            }
                            data.Venues[i].Devices = clearsky_response_json.devices;

                            //console.log(tem.Devices);

                        }
                    })
                        .catch(function (err) {
                            console.log('Error1 :-S', err);
                        });
                }
            )
                .catch(function (err) {
                    console.log('Error 2 :-S', err);
                })
            )



        }


    }

    return Promise.all(promises).then(function () {

        return data;
    })
}
