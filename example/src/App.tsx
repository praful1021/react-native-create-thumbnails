import * as React from 'react';
import { Image, StyleSheet, Text, View } from 'react-native';
import { createThumbnail } from 'react-native-create-thumbnails';
export default function App() {
  const [image, setImage] = React.useState('');

  React.useEffect(() => {
    const URL =
      'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4';
    createThumbnail(URL, (uri: any) => {
      console.log('URL', uri);
      setImage(uri);
    });
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Image source={{ uri: image }} style={styles.image} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  image: {
    width: 300,
    height: 300,
  },
});
