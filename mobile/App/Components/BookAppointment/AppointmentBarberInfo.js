import { View, Text, Image } from 'react-native'
import React,{ useState, useEffect }  from 'react'
import { PageHeader } from '../Shared/PageHeader'
import Colors from '../../../assets/Shared/Colors'
import {Ionicons} from '@expo/vector-icons'
import { HorizontalLine } from '../Shared/HorizontalLine'
import { API_BASE_URL } from '../../Config/apiConfig';
export function AppointmentBarberInfo({barber}) {
  
  const defaultImage = require('../../../assets/Images/image.png');
  const [barberImage, setBarberImage] = useState(defaultImage);

  useEffect(() => {
    if ( barber?.images.length > 0) {
      fetchImage(barber?.images[0].name);
    }
  }, [barber]);

  const fetchImage = async (imageName) => {
    
      const response = await fetch(`${API_BASE_URL}/${imageName}`);
      const imageData = await response.blob();
      const uri = URL.createObjectURL(imageData);
      setBarberImage({ uri });
    
  };
  return (
    <View>
      <PageHeader title={'Book Appointment'}/>
      <View style={{marginTop:
      40,
    display:'flex',
flexDirection:'row',
alignItems:'center',
gap:15}}>
        <Image  source={barberImage}
        style={{width:100,height:100,borderRadius:99} }
        />

<View>
    <Text style={{
        fontSize:20,
        marginBottom:8
    }}>{barber.name}  </Text>
    <View style={{ flexDirection: 'row', alignItems: 'center' }}>
            <Ionicons name="location" size={22} color={Colors.PRIMARY} />
            <Text style={{fontSize:16,color:Colors.GRAY,width:'70%'}}>{barber.address}</Text>
          </View>
</View>


      </View>
      <HorizontalLine/>
    </View>
  )
}