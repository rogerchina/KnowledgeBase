const fs = require('fs');

fs.watch('c:/test', {encoding: 'buffer'}, (eventType, filename) => {
	if(filename) {
		console.log(filename);
	}
});