module.exports = {
  env: {
    es2021: true,
    node: true,
    jest: true,
  },
  extends: ["plugin:@typescript-eslint/recommended", "standard", "prettier"],
  parser: "@typescript-eslint/parser",
  parserOptions: {
    ecmaVersion: 12,
    sourceType: "module",
  },
  plugins: ["@typescript-eslint"],
  rules: {
    "no-new": "off",
    camelcase: "off",
    "@typescript-eslint/no-non-null-assertion": "off",
    "@typescript-eslint/no-unused-vars": [
      "warn",
      { argsIgnorePattern: "^_.*", varsIgnorePattern: "^\\$" }, // $ 변수 무시 패턴 추가
    ],
    "no-eq-null": "error",
  },
  globals: {
    "$": true
  },
};