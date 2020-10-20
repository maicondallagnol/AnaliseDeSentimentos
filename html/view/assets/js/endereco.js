function addEndereco(logradouro_entry, numero_entry, complemento_entry, bairro_entry, cidade_entry, cep_entry, usuario_id_entry)
{
    const mutation = `
    mutation addEndereco($logradouro: String!, $numero: String!, $complemento: String, $bairro: String!,
                $cidade: String!, $cep: String!, $usuario_id: ID!){
                
        addEndereco(logradouro: $logradouro, numero: $numero, complemento: $complemento, bairro: $bairro, 
            cidade: $cidade, cep: $cep, id_usuario: $id_usuario)
        {
            id
            logradouro
            numero
            complemento
            bairro
            cidade
            cep
            usuario
            {
                id
                nome
                sobrenome
            }
        }
    }
    `
    const variaveis = {logradouro: logradouro_entry, numero: numero_entry, complemento: complemento_entry, bairro: bairro_entry, cidade: cidade_entry, cep: cep_entry, udu}

    return queryFetch(mutation, variaveis)
}


function queryFetch(query_entry, variables_entry)
{
    return fetch('http://localhost:8088/graphql',
        {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                query: query_entry,
                variables: variables_entry
            })
        }).then(res => res.json())
}