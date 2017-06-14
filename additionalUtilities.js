import fetch from 'node-fetch';

export function fetchSummary(data: any): Promise<any> {

        var venue_count: number = data.totalVenueCount;
        var promises = [];
        for (let i = 0; i < venue_count; i++) {
            var tem: any = data.Venues[i];
            tem.totalAttended = 0;
            tem.Devices;
            if(tem.events==null)
            {
                continue;
            }

            for (let j = 0; j < tem.events.length; j++) {
                var tem_event_id: string = tem.events[j];
                var myhash: any = {};    
            
                var clearsky_url = 'http://presence.clearsky.preprod.prod-tmaws.io/event/' + tem_event_id;
                promises.push(fetch(clearsky_url).then(
                    function (response) {
                        if (response.status !== 200) {
                            console.log('Looks like there was a problem. Status Code: ' + response.status);
                            return;
                        }
                        return response.json().then(function (data) {
                            tem.totalAttended += parseInt(data.totalAttended);

                            if(data.devices==null)
                            {
                                return ;
                            }
                            if (tem.Devices != null) {
                                for (var device_cnt = 0; device_cnt < data.devices.length; device_cnt++) {
                                    if(data.devices[device_cnt].horizonId in myhash)
                                    {
                                        var scan_cnt = myhash[data.devices[device_cnt].horizonId];
                                        scan_cnt+=data.devices[device_cnt].total;
                                        myhash[data.devices[device_cnt].horizonId] = scan_cnt;
                                        data.devices[device_cnt].total = scan_cnt;
                                        tem.Devices.push(data.devices[device_cnt]);
                                    }
                                    else{
                                        myhash[data.devices[device_cnt].horizonId] = data.devices[device_cnt].total;
                                        tem.Devices.push(data.devices[device_cnt]);
                                        }
                                }
                                
                                
                            }
                            else {
                                 for (var device_cnt = 0; device_cnt < data.devices.length; device_cnt++) 
                                 {
                                
                                myhash[data.devices[device_cnt].horizonId] = data.devices[device_cnt].total;

                                }
                                tem.Devices = data.devices;
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
