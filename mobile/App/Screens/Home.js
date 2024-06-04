// Home.js

import React from 'react';
import { ScrollView } from 'react-native';
import { Header } from '../Components/Home/Header';
import { SearchBar } from '../Components/Home/SearchBar';
import { Slider } from '../Components/Home/Slider';
import { Services } from '../Components/Home/Services';
import { BarberShops } from '../Components/Home/BarberShops';

export function Home({ navigation, customer }) {
    return (
        <ScrollView style={{ flex: 1, padding: 20 }}>
            <Header customer={customer}/>
            <SearchBar setSearchText={(value) => console.log(value)} />
            <Slider />
            <Services customer={customer} />
            <BarberShops customer={customer} />
        </ScrollView>
    );
}
