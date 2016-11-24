const dns = require('dns');

dns.lookup('www.baidu.com', (err, addresses, family)=>{
	if(err) throw err;
	
	console.log('addresses: ', addresses);
})