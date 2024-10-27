<!doctype html>
<html lang="pt-br">

<head>
    <title>Pagina sucesso usuario</title>
    <!-- Required meta tags -->
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />

    <style>
        .modal-dialog-centered {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .modal-header{
            align-self: center;
        }

        .modal-header .modal-title {
            font-size: 2rem;
            /* Ajusta o tamanho do título */
            text-align: center;
            
        }

        .modal-body {
            font-size: 1.5rem;
            /* Ajusta o tamanho do texto */
            text-align: center;
        }
    </style>


</head>

<body>
    <header>
        <!-- place navbar here -->
    </header>
    <main>

        <!-- Modal -->
        <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-danger" id="exampleModalLabel">Erro chame o ERICK AGORA!!</h5>

                    </div>
                    <div class="modal-body text-alert">
                        Voce falhou miseravelmente MALDITO!!!
                        <br>
                        Chame o Erick Imediamente pra te salvar
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <a href="/Trabalho_PI_2MA/" class="btn btn-danger">Ir para a pagina inicial</a>
                    </div>
                </div>
            </div>
        </div>

        

    </main>
    <footer>
        <!-- place footer here -->
    </footer>

    
    <script>
        // Abre a modal assim que o usuário entra na página
        document.addEventListener('DOMContentLoaded', function () {
            var myModal = new bootstrap.Modal(document.getElementById('myModal'));
            myModal.show();
        });
    </script>




    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>


</html>