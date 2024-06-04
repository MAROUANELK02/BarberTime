import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import Colors from '../../../assets/Shared/Colors';

export function SubHeading({ subHeadingTitle, onPressSeeAll, showAll,seelAll=true }) {
    return (
        <View style={{
            display: 'flex',
            flexDirection: 'row',
            justifyContent: 'space-between',
            alignItems: 'center',
            marginBottom: 10,
            marginTop:10
        }}>
            <Text style={{
                fontSize: 20,
            }}>{subHeadingTitle}</Text>
          {seelAll? <TouchableOpacity onPress={onPressSeeAll}>
                <Text style={{
                    color: Colors.PRIMARY
                }}>{showAll ? 'See Less' : 'See All'}</Text> 
            </TouchableOpacity>:null}
        </View>
    );
}
