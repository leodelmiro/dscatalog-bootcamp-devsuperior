import React, { useState } from 'react';
import { View, Text, ScrollView, TouchableOpacity, Image, Modal, TextInput, ActivityIndicator } from 'react-native';
import { colors, theme, text } from '../../../styles';
import arrow from '../../../assets/leftArrow.png'

interface FormProductProps {
    setScreen: Function;
}

const FormProduct:React.FC<FormProductProps> = (props) => {
    const { setScreen } = props;
    const [loading, setLoading] = useState(false);
    const [edit, setEdit] = useState(false);
    const [showCategories, setShowCategories] = useState(false);
    const [categories, setCategories] = useState([
        {
            id: 3,
            name: "Computador"
        },
        {
            id: 2,
            name: "Eletrônicos"
        },
        {
            id: 1,
            name: "Notebooks"
        },
        {
            id: 4,
            name: "Hardwares"
        },
        {
            id: 5,
            name: "Periféricos"
        }
    ])
    const [product, setProduct] = useState({
        name: null,
        description: null,
        image: null,
        price: null,
        categories: null
    });

    return (
        <View style={theme.formContainer}>
            { loading ? (
                <ActivityIndicator size="large" color={colors.mediumGray} />
            ) : (
                    <View style={theme.formCard}>
                        <ScrollView>
                            <Modal
                                visible={showCategories}
                                animationType="fade"
                                transparent={true}
                                presentationStyle="overFullScreen"
                            >
                                <View style={theme.modalContainer}>
                                    <ScrollView contentContainerStyle={theme.modalContent}>
                                        {categories.map((cat) => (
                                            <TouchableOpacity
                                                style={theme.modalItem}
                                                key={cat.id}
                                                onPress={() => {
                                                    setProduct({ ...product, categories: cat.name });
                                                    setShowCategories(!showCategories);
                                                }}>
                                                <Text>{cat.name}</Text>
                                            </TouchableOpacity>
                                        )
                                        )}
                                    </ScrollView>
                                </View>
                            </Modal>
                            <TouchableOpacity onPress={() => setScreen("products")} style={theme.goBackContainer}>
                                <Image source={arrow} />
                                <Text style={text.goBackText}>Voltar</Text>
                            </TouchableOpacity>
                            <TextInput placeholder="Nome do produto" style={theme.formInput}/>
                            <TouchableOpacity 
                                onPress={() => setShowCategories(!showCategories)} 
                                style={theme.selectInput}
                            >
                                <Text style={[product.categories === null && {color: colors.mediumGray}, product.categories !== null && {color: colors.black}]}>
                                    {product.categories === null
                                        ? "Escolha uma categoria"
                                        : product.categories}
                                </Text>
                            </TouchableOpacity>
                            <TextInput placeholder="Preço" style={theme.formInput}/>
                            <TouchableOpacity activeOpacity={0.8} style={theme.uploadBtn}>
                                <Text style={text.uploadText}>Carregar imagem</Text>
                            </TouchableOpacity>
                            <Text style={text.fileSize}>
                                As imagens devem ser JPG ou PNG e não devem ultrapassar 5 mb.
                            </Text>
                            <TextInput multiline placeholder="Descrição" style={theme.textArea}/>
                            <View style={theme.buttonContainer}>
                                <TouchableOpacity style={theme.deleteBtn}>
                                    <Text style={text.deleteText}>Cancelar</Text>
                                </TouchableOpacity>
                                <TouchableOpacity style={theme.saveBtn}>
                                    <Text style={text.saveText}>Salvar</Text>
                                </TouchableOpacity>
                            </View>
                        </ScrollView>
                    </View>
                )}

        </View>
    );
};

export default FormProduct;