function buscarEndereco() {
    const cep = document.getElementById('id-cep').value.trim();

    // Verifica se o CEP tem 8 dígitos numéricos
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

            // Preenche os campos com os dados retornados pela API
            document.getElementById('id-rua').value = data.logradouro;
            document.getElementById('id-bairro').value = data.bairro;
            document.getElementById('id-cidade').value = data.localidade;
            document.getElementById('id-complemento').value = data.complemento || '';

            // Seleciona o estado no campo <select>
            const estadoSelect = document.getElementById('id-estado');
            estadoSelect.value = data.uf;

            // Foco no próximo campo após a busca de CEP
            document.getElementById('id-numero').focus();
        })
        .catch(error => {
            console.error('Erro ao buscar o endereço:', error);
            alert('Ocorreu um erro ao buscar o endereço.');
        });
}
