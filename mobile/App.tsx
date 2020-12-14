import 'react-native-gesture-handler';
import React from 'react';
import { NavigationContainer } from '@react-navigation/native'
import {View, Text, StyleSheet} from 'react-native';
import Routes from './src/routes';

const App: React.FC = () => {
  return (
    <NavigationContainer>
      <Routes/>
    </NavigationContainer>
  );
}

export default App;