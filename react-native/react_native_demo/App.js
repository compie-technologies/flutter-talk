/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Fragment} from 'react';
import {
  SafeAreaView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {constants} from "./src/consts";
import MovieList from "./src/movie_list";

const App = () => {
  return (
    <Fragment>
      <StatusBar barStyle="default" />
      <SafeAreaView>
        <View style={{height: 54, backgroundColor: '#3F51B5', justifyContent: 'center'}} >
          <Text style={{color: 'white', fontSize: 24, marginStart: 10}} >
            {constants.appTitle}
          </Text>
        </View>
        <MovieList />
      </SafeAreaView>
    </Fragment>
  );
};

export default App;
