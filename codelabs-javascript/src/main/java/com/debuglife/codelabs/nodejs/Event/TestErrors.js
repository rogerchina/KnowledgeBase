const EventEmitter = require('events');
const ee = new EventEmitter();

setImmediate(()=>{
	ee.emit('error', new Error('This will crash'));
});