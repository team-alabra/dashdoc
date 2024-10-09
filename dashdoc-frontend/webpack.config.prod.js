const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const Dotenv = require('dotenv-webpack');

module.exports = {
  mode: 'production',
  entry: path.resolve(__dirname, 'src', 'index.tsx'),
  output: {
    filename: 'bundle.js',
  },
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx'],
    alias: {
      '@tests': path.resolve(__dirname, '/src/test/'),
      '@components': path.resolve(__dirname, '/src/components/'),
      '@pages': path.resolve(__dirname, '/src/pages/'),
      '@constants': path.resolve(__dirname, '/src/utils/constants.ts'),
      '@typings': path.resolve(__dirname, '/src/typings/'),
      '@styles': path.resolve(__dirname, '/src/styles/'),
      '@shared': path.resolve(__dirname, '/src/components/shared/'),
      '@services': path.resolve(__dirname, '/src/services/'),
      '@hooks': path.resolve(__dirname, '/src/hooks'),
      '@tests': path.resolve(__dirname, '/src/tests/'),
      '@api': path.resolve(__dirname, '/src/api/'),
      '@utils': path.resolve(__dirname, '/src/utils/')
    }
  },
  plugins: [
    // fix "process is not defined" error:
    new webpack.ProvidePlugin({
      process: 'process/browser',
    }),
    new HtmlWebpackPlugin({
      title: "Dashdoc",
      template: "public/index.html",
    }),
    new Dotenv()
  ],
  module: {
    rules: [
      {
        test: /\.(t|j)sx?$/,
        exclude: [/node_modules/],
        loader: 'ts-loader',
      },
      {
        enforce: 'pre',
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'source-map-loader',
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.(png|jpg|gif|svg)$/i,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 8192,
            },
          },
        ],
      },
    ],
  },
  performance: {
    hints: false,
    maxEntrypointSize: 512000,
    maxAssetSize: 512000,
  },
};
