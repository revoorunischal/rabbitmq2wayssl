var fs = require('fs');
var opts = {
  cert: fs.readFileSync('/Users/nischal/Documents/keys2/iot-public.pem'),
  key: fs.readFileSync('/Users/nischal/Documents/keys2/iot-private.pem'),
  ca: [fs.readFileSync('/Users/nischal/Documents/keys2/iot-truststore.pem')],
};
var amqp = require('amqplib');
var open = amqp.connect('amqps://prat:5671', opts);
open.then(function(conn) {
  console.log(conn);
}).then(null, console.warn);
