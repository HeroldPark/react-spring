module.exports = {
    module: {
      rules: [
        {
          test: /\.scss$/,
          use: [
            'style-loader',
            'css-loader',
            {
              loader: 'sass-loader',
              options: {
                sourceMap: false, // Disable source maps for Sass
              },
            },
          ],
        },
      ],
    },
  };
  