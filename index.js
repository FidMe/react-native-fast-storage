import { NativeModules, Platform } from 'react-native';

const { RNFastStorage } = NativeModules;

if (Platform.OS === 'ios' && RNFastStorage.setupLibrary) RNFastStorage.setupLibrary();

export default {
  ...RNFastStorage,
  multiGet: (keys) =>
    Promise.all(
      keys.map(async (key) => [key, await RNFastStorage.getItem(key)]),
    ),
  multiSet: (keyValuePairs) =>
    Promise.all(
      keyValuePairs.map(([key, value]) => RNFastStorage.setItem(key, value)),
    ),
  multiRemove: (keys) =>
    Promise.all(keys.map((key) => RNFastStorage.removeItem(key))),
};
