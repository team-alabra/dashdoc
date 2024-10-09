import type {Config} from 'jest';

const config: Config = {
testEnvironment: "jsdom",
verbose: true,
moduleNameMapper: {
  "^@/(.*)$": "<rootDir>/src/$1",
  "^@shared(.*)$": "<rootDir>/src/components/shared$1",
  "^@constants": "<rootDir>/src/utils/constants.ts",
  "^@typings(.*)$": "<rootDir>/src/typings$1",
  "^@styles(.*)$": "<rootDir>/src/styles$1",
  "^@components(.*)$": "<rootDir>/src/components$1",
  "^@services(.*)$": "<rootDir>/src/services$1",
  "^@hooks(.*)$": "<rootDir>/src/hooks$1",
  "^@tests(.*)$": "<rootDir>/src/tests$1",
  "^@api(.*)$": "<rootDir>/src/api$1",
  "^@utils(.*)$": "<rootDir>/src/utils$1"
},
setupFilesAfterEnv: ['<rootDir>/src/tests/setupTests.tsx']
};

export default config;
