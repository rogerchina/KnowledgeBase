var crypto1;
try {
  crypto1 = require('crypto');
  console.log('crypto support is enabled.');
} catch (err) {
  console.log('crypto support is disabled!');
}

const crypto = require('crypto');
const secret = 'abcdefg';
const hash = crypto.createHmac('sha256', secret)
                   .update('I love cupcakes')
                   .digest('hex');
console.log(hash);