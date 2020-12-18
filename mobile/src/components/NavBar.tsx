import React, { useState, useEffect } from 'react';
import { useNavigation, useRoute } from '@react-navigation/native';
import { TouchableOpacity, Image, View, Text } from 'react-native';
import { TouchableOpacity as Touchable } from 'react-native-gesture-handler'
import menu from '../assets/menu.png';
import { nav, text } from '../styles';
import { doLogout, isAuthenticated } from '../services/auth';

const NavBar: React.FC = () => {
    const [show, setShow] = useState(false);
    const [authenticated, setAuthenticated] = useState(false);
    const navigation = useNavigation();
    const route = useRoute();

    function navigate(path: any) {
        if (path) {
            setShow(false);
            navigation.navigate(path);
        }

        setShow(false);
    }

    async function logged() {
        const result = await isAuthenticated();

        result ? setAuthenticated(true) : setAuthenticated(false);
    }

    useEffect(() => {
        logged();
    }, []);

    function logout() {
        doLogout();
        navigation.navigate("Login");
    }

    return (

        <>
            { authenticated ? (
                <TouchableOpacity style={nav.logoutBtn} onPress={() => logout()}>
                    <Text style={text.logoutText}>Sair</Text>
                </TouchableOpacity>
            ) : 
            (
                <TouchableOpacity activeOpacity={0.8} style={nav.drawer} onPress={() => setShow(!show)}>
                    <Image source={menu} />
                    { show ? (
                        <View style={nav.options}>
                            <Touchable 
                                style={nav.option} 
                                onPress={() => navigate("Home")   
                            }
                            >
                                <Text 
                                    style={[
                                        nav.textOption,
                                        route.name === "Home" ? nav.textActive : null
                                    ]}
                                >
                                    Home
                                </Text>
                            </Touchable>
                            <Touchable 
                                style={nav.option} 
                                onPress={() => navigate("Catalog")}
                            >
                                <Text 
                                    style={[
                                        nav.textOption,
                                        route.name === "Catalog" ? nav.textActive : null
                                    ]}
                                >
                                    Catálogo
                                </Text>
                            </Touchable>
                            <Touchable 
                                style={nav.option} 
                                onPress={() => navigate("Login")}
                            >
                                <Text 
                                    style={[
                                        nav.textOption,
                                        route.name === "ADM" ? nav.textActive : null
                                    ]}
                                >
                                    ADM
                                </Text>
                            </Touchable>
                        </View>
                    ) : null}
                </TouchableOpacity>
            )}
        </>
        
    );
};

export default NavBar;