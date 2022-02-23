import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-create-thumbnails' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const VideoThumbnails = NativeModules.VideoThumbnails
  ? NativeModules.VideoThumbnails
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return VideoThumbnails.multiply(a, b);
}

export function createThumbnail(uri: string, callback: any) {
  return VideoThumbnails.createThumbnail(uri, callback);
}
