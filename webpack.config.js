const path = require('path');
const WebpackZipPlugin = require('webpack-zip-plugin');

module.exports = {
    entry: './target/main.js',
    module: {
        rules: [
          {
            exclude: /node_modules/
          }
        ]
      },
      plugins: [
        new WebpackZipPlugin({
            frontShell: 'pwd',
            initialFile: 'target',
            endPath: './',
            zipName: 'lambda.zip'
        })
      ]
};
