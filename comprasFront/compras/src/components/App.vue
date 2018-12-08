<template>
    <div class="app" id="app">
        <a href="https://icons8.com"></a>
        <div id="wrapper" style="background-color:  rgba(0, 0, 0, 0.6);width: 100%; height: 100%;  ">


            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="transicao sidebar-brand">
                        <a href="#">

                            <b-img v-on:click="tela=0"
                                    v-if="tela!=0"  center
                                   class="transicao-foto"
                                   style="padding-top: 15px"
                                   src="https://img.icons8.com/nolan/100/000000/shopping-cart.png"
                                   alt="left image"/>

                        </a>
                    </li>
                    <li class="inicio" v-on:click="tela=0">
                        <a href="#" v-on:click="tela=2">
                            <img v-if="tela==0" class="imagem-botao transicao-foto" src="https://img.icons8.com/small/10/D36BF8/full-stop.png">
                            <img class="imagem-botao" src="https://img.icons8.com/nolan/25/ffffff/room.png">Inicio</a>
                    </li>
                    <li v-on:click="tela=2">
                        <a href="#">
                            <img v-if="tela==2" class="imagem-botao transicao-foto" src="https://img.icons8.com/small/10/D36BF8/full-stop.png">
                            <img class="imagem-botao" src="https://img.icons8.com/nolan/25/ffffff/low-price.png">Cadastrar Categoria</a>
                    </li>

                    <li v-on:click="tela=5">
                        <a href="#">
                            <img v-if="tela==5"  class="imagem-botao transicao-foto" src="https://img.icons8.com/small/10/D36BF8/full-stop.png">
                            <img class="imagem-botao" src="https://img.icons8.com/nolan/25/000000/table-1.png">Deletar
                        Categoria</a>
                    </li>
                    <li v-on:click="tela=3">

                        <a href="#">
                            <img v-if="tela==3" class="imagem-botao transicao-foto" src="https://img.icons8.com/small/10/D36BF8/full-stop.png">
                            <img class="imagem-botao" src="https://img.icons8.com/nolan/25/ffffff/box.png">Cadastrar
                            Produto</a>
                    </li>
                    <li v-on:click="tela=4">
                        <a href="#">
                            <img v-if="tela==4"  class="imagem-botao transicao-foto" src="https://img.icons8.com/small/10/D36BF8/full-stop.png">
                            <img class="imagem-botao" src="https://img.icons8.com/nolan/25/000000/table-1.png">Lista
                            de Produtos</a>
                    </li>
                </ul>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <input v-b-tooltip.hover title="Abrir o menu" v-if="aparecer" v-on:click="aparecer=false"
                           type="image"
                           src="https://img.icons8.com/nolan/50/000000/right.png"
                           id="entrar"/>
                    <input v-b-tooltip.hover title="Fechar o menu" v-if="!aparecer" v-on:click="aparecer=true"
                           type="image"
                           src="https://img.icons8.com/nolan/50/000000/left.png" id="sair">


                    <InicioLogo class="transicao" v-show="tela==0"></InicioLogo>
                    <CadastroProduto v-show="tela==3"></CadastroProduto>
                    <CadastroCategoria v-show="tela==2"></CadastroCategoria>
                    <TabelasProduto v-show="tela==4"></TabelasProduto>
                    <ExcluirCategoria v-show="tela==5"></ExcluirCategoria>

                </div>
            </div>

        </div>


    </div>
</template>

<script>

    import CadastroProduto from './Cadastro-Produto.vue'
    import CadastroCategoria from './Cadastro-Catgoria.vue'
    import TabelasProduto from './Tablelas-Produto.vue'
    import InicioLogo from './Inicio-Logo.vue'
    import ExcluirCategoria from './Excluir-Categoria.vue'

    export default {
        name: 'app',
        components: {
            CadastroProduto,
            CadastroCategoria,
            TabelasProduto,
            InicioLogo,
            ExcluirCategoria
        },
        data() {
            return {
                aparecer: true,
                tela: 0
            }
        }, mounted() {
            $("#entrar").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });

            $("#sair").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });


        }
    }
</script>

<style lang="scss">

    .transicao {

        animation-name: profile-titulo;
        animation-duration: 4s;
    }



    .transicao-foto {

        animation-name: profile-titulo;
        animation-duration: 0.4s;
    }

    .imagem-botao {
        margin-right: 10px;
    }

    @keyframes profile-titulo {
        0% {
            opacity: 0;
            transform: translate(-20px, 0px)
        }

        100% {
            opacity: 1;
            transform: translate(0px, 0px)
        }
    }

    .app {
        background-image: url('../img/fundo.jpg');
        background-size: auto;

        width: 100%;
        height: 100%;
        position: absolute;
    }

    .inicio {

        margin-top: 80px;
        border-top-width: 30px;

    }

    body {
        overflow-x: hidden;
    }

    #wrapper {
        padding-left: 0;
        -webkit-transition: all 0.5s ease;
        -moz-transition: all 0.5s ease;
        -o-transition: all 0.5s ease;
        transition: all 0.5s ease;
    }

    #wrapper.toggled {
        padding-left: 250px;
    }

    #sidebar-wrapper {
        z-index: 1000;
        position: fixed;
        left: 250px;
        width: 0;
        height: 100%;
        margin-left: -250px;
        overflow-y: auto;
        background: #000;
        -webkit-transition: all 0.5s ease;
        -moz-transition: all 0.5s ease;
        -o-transition: all 0.5s ease;
        transition: all 0.5s ease;
    }

    #wrapper.toggled #sidebar-wrapper {
        width: 250px;
    }

    #page-content-wrapper {
        width: 100%;
        position: absolute;
        padding: 15px;
    }

    #wrapper.toggled #page-content-wrapper {
        position: absolute;
        margin-right: -250px;
    }

    /* Sidebar Styles */

    .sidebar-nav {
        position: absolute;
        top: 0;
        width: 250px;
        margin: 0;
        padding: 0;
        list-style: none;
    }

    .sidebar-nav li {
        text-indent: 20px;
        line-height: 40px;
    }

    .sidebar-nav li a {
        display: block;
        text-decoration: none;
        color: #999999;
    }

    .sidebar-nav li a:hover {
        text-decoration: none;
        color: #fff;
        background: rgba(255, 255, 255, 0.2);
    }

    .sidebar-nav li a:active, .sidebar-nav li a:focus {
        text-decoration: none;
    }

    .sidebar-nav > .sidebar-brand {
        height: 65px;
        font-size: 18px;
        line-height: 60px;
    }

    .sidebar-nav > .sidebar-brand a {
        color: #999999;
    }

    .sidebar-nav > .sidebar-brand a:hover {
        color: #fff;
        background: none;
    }

    @media(min-width: 768px) {
        #wrapper {
            padding-left: 0;
        }
        #wrapper.toggled {
            padding-left: 250px;
        }
        #sidebar-wrapper {
            width: 0;
        }
        #wrapper.toggled #sidebar-wrapper {
            width: 250px;
        }
        #page-content-wrapper {
            padding: 20px;
            position: relative;
        }
        #wrapper.toggled #page-content-wrapper {
            position: relative;
            margin-right: 0;
        }
    }

    //barra de rolagem

    ::-webkit-scrollbar {
        width: 10px;
        height: 10px;
        -webkit-border-radius: 16px;
    }

    ::-webkit-scrollbar-track-piece {
        background-color: black;
        -webkit-border-radius: 3px;
    }

    ::-webkit-scrollbar-thumb:vertical {
        height: 5px;
        background-color: black;
        -webkit-border-radius: 3px;
    }

    ::-webkit-scrollbar-thumb:horizontal {
        width: 5px;
        background-color: black;
        -webkit-border-radius: 3px;
    }


</style>
