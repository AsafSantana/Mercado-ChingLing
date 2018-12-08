<template>
    <div class="transicao">

        <div class="container">
            <div class="container "
                 style="color: white; border-radius: 19px; padding: 2%;  background-color: rgba(0, 0, 0, 0.7); ">

                <h2 class="central" style="padding: 20px">Lista de Produtos</h2>
                <!-- User Interface controls -->
                <b-row>
                    <b-col class="my-1">
                        <b-form-group horizontal label="Filtrar" class="mb-5">
                            <b-input-group>
                                <b-form-input v-model="filter" placeholder="Digite uma caratceristica"/>
                                <b-input-group-append>
                                    <b-button style="opacity: 0.9" variant="primary" :disabled="!filter"
                                              @click="filter = ''">Limpar
                                    </b-button>

                                </b-input-group-append>
                            </b-input-group>
                        </b-form-group>
                    </b-col>
                    <b-col md="6" class="my-1">

                    </b-col>


                </b-row>

                <!-- Main table element -->
                <b-table show-empty
                         stacked="md"
                         :items="items"
                         :fields="fields"
                         :current-page="currentPage"
                         :per-page="perPage"
                         :filter="filter"
                         :sort-by.sync="sortBy"
                         :sort-desc.sync="sortDesc"
                         :sort-direction="sortDirection"
                         @filtered="onFiltered"
                >
                    <template slot="name" slot-scope="row">{{row.value.first}} {{row.value.last}}</template>
                    <template slot="isActive" slot-scope="row">{{row.value?'Yes :)':'No :('}}</template>
                    <template slot="actions" slot-scope="row">
                        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                        <b-button size="sm" variant="danger" @click.stop="info(row.item, row.index, $event.target)"
                                  class="mr-1">
                            Excluir
                        </b-button>
                        <b-button size="sm" variant="danger" @click.stop="editar(row.item, row.index, $event.target)">
                            Editar
                        </b-button>
                    </template>

                    <template slot="row-details" slot-scope="row">
                        <b-card>
                            <ul>
                                <li v-for="(value, key) in row.item" :key="key">{{ key }}: {{ value}}</li>
                            </ul>
                        </b-card>
                    </template>
                </b-table>

                <b-row>
                    <b-col md="6" class="my-1">
                        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage"
                                      class="my-0"/>
                    </b-col>
                </b-row>

                <b-modal ref="myModalRef" hide-footer>
                    <div class="d-block text-center">
                        <b-img center src="https://img.icons8.com/nolan/100/D36BF8/checkmark.png"></b-img>
                        <h3 style="color: black">{{msg}}</h3>
                    </div>
                    <b-btn class="mt-3" variant="primary" block @click="hideModal">Fechar</b-btn>
                </b-modal>
            </div>
        </div>

        <!-- Info modal -->
        <b-modal id="modalInfo" @hide="resetModal" hide-footer>
            <form>
                <div class="form-group input-espaco">
                    <label>Categoria do Produto</label>
                    <b-form-select id="exampleInput3"
                                   :options="categorias"
                                   required
                                   v-model="produtoSalvar.categoria"
                    >

                    </b-form-select>
                </div>

                <div class="form-group input-espaco"><label>Nome do produto</label>
                    <b-form-input v-model="produtoSalvar.nome"
                                  class="form-control nome" type="text" id="nome">
                    </b-form-input>
                </div>

                <div class="form-group input-espaco"><label>Preço</label>
                    <b-form-input v-model="produtoSalvar.preco"
                                  class="form-control preco"
                                  type="number" id="preco">
                    </b-form-input>
                </div>

                <div class="form-group central">
                    <button class="btn btn-primary btn-block " v-on:click="salvar()" type="reset">Salvar
                    </button>
                </div>
            </form>


        </b-modal>

    </div>
</template>

<script>

    const items = []
    export default {

        name: "Cadastro-Produto",
        data() {
            return {
                items: items,
                fields: [
                    {key: 'nome', label: 'Nome do produto', sortable: true, sortDirection: 'desc'},
                    {key: 'preco', label: 'Preço', sortable: true, 'class': 'text-center'},
                    {key: 'categoria', label: 'Categoria', sortable: true, 'class': 'text-center'},
                    {key: 'actions', label: 'Ações'},
                ],
                currentPage: 1,
                perPage: 5,
                totalRows: items.length,
                pageOptions: [5, 10, 15],
                sortBy: null,
                sortDesc: false,
                sortDirection: 'asc',
                filter: null,
                modalInfo: {title: '', content: ''},
                form: {
                    categorias: null,
                },
                categorias: [],
                produtoSalvar: {
                    id: '',
                    nome: '',
                    preco: '',
                    categoria: []
                },
                msg: ' '
            }
        },
        computed: {
            sortOptions() {
                // Create an options list from our fields
                return this.fields
                    .filter(f => f.sortable)
                    .map(f => {
                        return {text: f.label, value: f.key}
                    })
            }
        },
        methods: {

            info(item) {

                this.$http.delete('http://localhost:8081/produtos/' + item.id).then(response => {
                    this.items = response.body;
                    this.msg = "Produto deletado com sucesso!";
                    this.showModal();
                    this.atualizarProduto();

                });
            },

            editar(item, index, button) {
                this.modalInfo.content = JSON.stringify(item, null, 2);
                this.$root.$emit('bv::show::modal', 'modalInfo', button)
            },

            salvar() {
                var produto = JSON.parse(this.modalInfo.content);
                this.produtoSalvar.id = produto.id;
                this.$http.put('http://localhost:8081/produtos/' + produto.id + '?idCategoria=' + this.produtoSalvar.categoria, this.produtoSalvar).then(response => {
                    this.$eventHub.$emit('produto-salvo');
                    this.msg = "Produto editado com sucesso!";
                    this.atualizarCategoria();
                    this.showModal();
                }, response => {

                });
            },


            onFiltered(filteredItems) {
                // Trigger pagination to update the number of buttons/pages due to filtering
                this.totalRows = filteredItems.length;
                this.currentPage = 1
            },
            atualizarProduto() {

                this.$http.get('http://localhost:8081/produtos').then(response => {

                    this.items = response.body;
                });
            },

            atualizarCategoria() {
                this.$http.get('http://localhost:8081/categorias').then(response => {
                    this.categorias = response.body;
                });
            },

            showModal() {
                this.$refs.myModalRef.show()
            },
            hideModal() {
                this.$refs.myModalRef.hide()
            },

        },
        created() {
            this.atualizarProduto();
            this.atualizarCategoria();

            this.$eventHub.$on('produto-salvo', this.atualizarProduto);
            this.$eventHub.$on('categoria-salva', this.atualizarCategoria);

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

    .central {
        padding: 1% !important;
        text-align: center;
    }

    .wrap {
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