<template>
    <div class="transicao">

        <div class="wrapper fadeInDown">
            <div id="formContent">

                <div class="container">
                    <div style="padding: 20px">
                        <h2 class="central">Deletar Categoria</h2>
                    </div>
                    <form>
                        <div class="form-group input-espaco">
                            <label>Listas de Categorias</label>
                            <b-form-select id="exampleInput3"
                                           :options="categorias"
                                           required
                                           v-model="produtoExcluir">
                            </b-form-select>
                        </div>


                        <div class="form-group central">
                            <button class="btn btn-danger btn-block " v-on:click="excluir()" type="reset" variant="danger">Excluir
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <b-modal ref="myModalRef" hide-footer>
            <div class="d-block text-center">
                 <h3> {{msg}}</h3>
            </div>
            <b-btn class="mt-3"   variant="primary" block @click="hideModal">Fechar</b-btn>
        </b-modal>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                form: {
                    categorias: null,
                },
                categorias: [],
                produtoExcluir:{

                },
                msg: "",

            }

        },
        created() {
            this.$http.get('http://localhost:8081/categorias').then(response => {
                this.categorias = response.body;


            });

            this.$eventHub.$on('categoria-salva', this.atualizar);
        },
        methods: {

            excluir(){
                console.log(this.produtoExcluir);
                this.$http.delete('http://localhost:8081/categorias/' + this.produtoExcluir).then(response => {
                    this.categorias = response.body;
                    this.msg = 'Categoria apagada com sucesso!';
                    this.$eventHub.$emit('categoria-salva');
                    this.showModal();
                    this.atualizar();

                }, response => {
                    this.msg = 'Categoria não pode ser apagada!';
                    this.showModal();
                });
            },

            atualizar() {
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