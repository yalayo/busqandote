const path = require('path');
const WebpackZipPlugin = require('webpack-zip-plugin');

module.exports = {
    entry: './target/main.js',
    output: {
      filename: 'main.js',
      path: path.resolve(__dirname, 'build')
    },
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
            initialFile: 'build',
            endPath: './',
            zipName: 'lambda.zip',
            behindShell: 'scp lambda.zip ./build'
        })
      ]
};