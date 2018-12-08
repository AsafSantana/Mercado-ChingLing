<template>
    <div class="transicao">

        <div class="wrapper fadeInDown">
            <div id="formContent">

                <div class="container">
                    <div style="padding: 20px">
                        <h2 class="central">Cadastro de Produtos</h2>
                    </div>
                    <form>
                        <div class="form-group input-espaco">
                            <label>Categoria do Produto</label>
                            <b-form-select id="exampleInput3"
                                           :options="categorias"
                                           required
                                           v-model="produtoSalvar.categoria">
                            </b-form-select>
                        </div>

                        <div class="form-group input-espaco"><label>Name do produto</label>
                            <b-form-input v-model="produtoSalvar.nome"
                                          class="form-control item" type="text" id="name">

                            </b-form-input>
                        </div>

                        <div class="form-group input-espaco"><label>Preço</label>
                            <b-form-input v-model="produtoSalvar.preco"
                                          class="form-control item"
                                          type="number" id="subject">
                            </b-form-input>
                        </div>

                        <div class="form-group central">
                            <button class="btn btn-primary btn-block " v-on:click="salvar()" type="reset">Salvar
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <b-modal ref="myModalRef"  hide-footer >
            <div class="d-block text-center">
                <b-img center src="https://img.icons8.com/nolan/100/D36BF8/checkmark.png"></b-img>
                <h3>Produto cadastrado com sucesso!</h3>
            </div>
            <b-btn class="mt-3" variant="primary" block @click="hideModal">Fechar</b-btn>
        </b-modal>
    </div>
</template>

<script>

    export default {
        name: "Cadastro-Produto",
        data() {
            return {
                form: {
                    categorias: null,
                },
                categorias: [],
                produtoSalvar: {
                    nome: '',
                    preco: '',
                    categoria: []
                }
            }

        },
        created() {
            this.$http.get('http://localhost:8081/categorias').then(response => {
                this.categorias = response.body;


            });


                this.$eventHub.$on('categoria-salva',this.atualizar);
        },
        methods: {
            salvar() {


                this.$http.post('http://localhost:8081/produtos?idCategoria='+this.produtoSalvar.categoria,this.produtoSalvar).then(response => {
                    this.$eventHub.$emit('produto-salvo');
                    this.showModal();
                }, response => {

                });
            },

            atualizar(){
                this.$http.get('http://localhost:8081/categorias').then(response => {
                    this.categorias = response.body;
                });
            },

            showModal() {
                this.$refs.myModalRef.show()
            },
            hideModal() {
                this.$refs.myModalRef.hide()
            }

        }
    }
</script>

<style>

    .transicao {

        animation-name: profile-titulo;
        animation-duration: 0.6s;
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

    .input-espaco {
        padding: 5px;
    }

    .central {
        padding: 5%;
        text-align: center;
    }

    .wrapper {
        color: white;
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
        width: 100%;
        min-height: 100%;
        padding: 100px;
    }

    #formContent {
        -webkit-border-radius: 10px 10px 10px 10px;
        border-radius: 10px 10px 10px 10px;
        background-color: rgba(0, 0, 0, 0.7);
        padding: 30px;
        width: 90%;
        max-width: 450px;
        position: relative;
        padding: 0px;
        -webkit-box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
        box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);

    }

    @-webkit-keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    @keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    /* Simple CSS3 Fade-in Animation */
    @-webkit-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-moz-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

</style>