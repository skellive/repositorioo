import React, {Component} from 'react'
import {View, Text, StyleSheet} from 'react-native'

class repositorioo extends Component {
    render(){

        return(
            <View style={styles.container}>
                <Text>Hola </Text>
            </View>
        )

    }

}

const styles = StyleSheet.create({
    container : {
        flex : 1,
        justifyContent : 'center',
        alignItems : 'center',
        backgroundColor : 'white'

    }
})

export default repositorioo