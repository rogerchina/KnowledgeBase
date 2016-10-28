
const fs = require('fs');
fs.rename('c:/test/test1/hello.txt', 'c:/test/test1/hellonew.txt', (err) => {
	if(err) {
		throw err;
	}
	console.log("succeed.");
});