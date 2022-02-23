# react-native-create-thumbnails

Generate the video thumbnails from the Remote URL

## Installation

```sh
npm install react-native-create-thumbnails
```

or

```sh
yarn add react-native-create-thumbnails
```

## Usage

```js
import { createThumbnail } from 'react-native-create-thumbnails';

// ...

createThumbnail(URL, (uri: any) => {
  console.log('URL', uri);
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
