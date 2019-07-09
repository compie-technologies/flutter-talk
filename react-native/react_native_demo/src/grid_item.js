import React, {Component} from 'react';
import {Text, View} from "react-native";
import ProgressiveImage from "./progressive_image";

class GridItem extends Component {
    constructor(props) {
        super(props);

        this._onLayout = this._onLayout.bind(this);

        this.state = {
            height: 0,
        }
    }

    render() {
        return (
            <View
                onLayout={this._onLayout}
                style={{ flex: 1, flexDirection: 'column', margin: 1, height: this.state.height }}
            >
                <ProgressiveImage style={{position: 'absolute', top: 0, right: 0, left: 0, bottom: 0, backgroundColor: 'white'}} url={this.props.imageUrl} />
                <View
                    style={{position: 'absolute', bottom: 0, left: 0, right:0, padding: 10, backgroundColor: 'rgba(0,0,0,0.5)'}}
                >
                    <Text style={{color: 'white', fontSize: 20,}} >{this.props.title}</Text>
                    <Text style={{color: '#9E9E9E', fontSize: 14}} >{this.props.genres}</Text>
                </View>
            </View>
        );
    }

    _onLayout(event) {
        const containerWidth = event.nativeEvent.layout.width;

        this.setState({height: containerWidth * 1.5})
    }
}

export default GridItem;