
document.getElementById('id-cadastrese').addEventListener('click', function() {
     // Seleciona os elementos para ocultar
     document.querySelector('.section-boasVindas').style.display = 'none';
     document.querySelector('.sobre').style.display = 'none';
     document.querySelector('.section-vagas').style.display = 'none';
     document.querySelector('.section-1').style.display = 'none';
     document.querySelector('.section-2').style.display = 'none';
     document.querySelector('.section-3').style.display = 'none';
     document.getElementById('depCliente').style.display = 'none';
     document.querySelector('.depoimento').style.display = 'none';



     // Exibe o iframe de cadastro
     document.getElementById('id-formCadastro').style.display = 'block';


 });



 function buscarEndereco() {
    const cep = document.getElementById('cep').value;

    if (cep.length !== 8 || isNaN(cep)) {
        alert('Por favor, insira um CEP válido com 8 dígitos.');
        return;
    }

    const url = `https://viacep.com.br/ws/${cep}/json/`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao consultar o CEP');
            }
            return response.json();
        })
        .then(data => {
            if (data.erro) {
                alert('CEP não encontrado.');
                return;
            }

            // Atualiza os campos com os dados retornados pela API
            document.getElementById('rua').textContent = data.logradouro;
            document.getElementById('bairro').textContent = data.bairro;
            document.getElementById('cidade').textContent = data.localidade;
            document.getElementById('estado').textContent = data.uf;
        })
        .catch(error => {
            console.error('Erro ao buscar o endereço:', error);
            alert('Ocorreu um erro ao buscar o endereço.');
        });
}

