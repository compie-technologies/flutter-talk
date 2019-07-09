import React, {Component} from 'react';
import { View, Image, StyleSheet } from 'react-native';

class ProgressiveImage extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <View style={this.props.style}>
                <Image
                    source={require('./images/placeholder.jpeg')}
                    style={styles.imageOverlay}
                    resizeMode={'stretch'}
                />
                <Image
                    source={{uri: this.props.url}}
                    style={styles.imageOverlay}
                    resizeMode={'cover'}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    imageOverlay: {
        position: 'absolute',
        left: 0,
        right: 0,
        bottom: 0,
        top: 0,
    },
});

export default ProgressiveImage;