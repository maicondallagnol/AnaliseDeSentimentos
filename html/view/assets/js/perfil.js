window.onload = getInfoUser()


function getInfoUser()
{
  
    query = `
    query getUsuario($id_usuario: ID!)
    {
        getUsuario(id_usuario: $id_usuario)
        {
            nome
            sobrenome
            endereco
            {
                rua
                numero
                cep
                cidade
                estado
            }
            contato
            {
                email
                celular
            }
            sexo
            data_nasc

            login
            {
                username
            }
        }
    }
    `

    variaveis = {id_usuario: localStorage.id}

    queryFetch(query, variaveis).then(data => 
        {
            if(!data.data){
                document.location.href = "login.html"
            }
            else
            {
                username = document.getElementById("username")
                nome_entry = document.getElementById('nome')
                sobrenome_entry = document.getElementById('sobrenome')
                sexo = document.getElementById('sexo')
                data_nasc = document.getElementById('data_nasc')
                email = document.getElementById('email')
                celular = document.getElementById('celular')
                username = document.getElementById('username')
                endereco = document.getElementById('endereco')
                cep = document.getElementById('cep')
                cidade = document.getElementById('cidade')
                estado = document.getElementById('estado')


                username.placeholder = data.data.getUsuario.login.username
                nome_entry.placeholder = data.data.getUsuario.nome
                sobrenome_entry.placeholder = data.data.getUsuario.sobrenome
                endereco.placeholder = data.data.getUsuario.endereco.rua + ", " + data.data.getUsuario.endereco.numero
                cep.placeholder = data.data.getUsuario.endereco.cep
                cidade.placeholder =data.data.getUsuario.endereco.cidade
                estado.placeholder = data.data.getUsuario.endereco.estado
                email.placeholder = data.data.getUsuario.contato.email
                celular.placeholder = data.data.getUsuario.contato.celular
                data_nasc.placeholder = data.data.getUsuario.data_nasc
                sexo.placeholder = data.data.getUsuario.sexo
            }
            
        })
}


function exit() {localStorage.id = 0}


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