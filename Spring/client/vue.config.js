const target = "http://localhost:8080";

module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      "/api": {
        target,
        changeOrigin: true,
        pathRewrite: {
          "^/api": "",
        },
      },
    },
  },
};
