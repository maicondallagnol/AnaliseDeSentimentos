window.onload = getSentimentos()

const histSelection = document.getElementById('sent-rows')

function getSentimentos()
{
    const query = `
    query{
        getAllSentimento
        {
            id
            nome
            imagem
            {
                caminho
            }
        }
    }
    `
    const variaveis = {}

    queryFetch(query, variaveis).then(data =>{
            data.data.getAllSentimento.forEach(sentimento => {
                var div = document.createElement("div")
                div.className = "col-lg-4"

                var div2 = document.createElement("div")
                div2.className = "card mb-3"

                var div3 = document.createElement("div")
                div3.className = "card-body text-center shadow"

                var p = document.createElement("button")
                p.textContent = sentimento.nome
                p.className = "btn btn-primary btn-sm"
                p.height = "50"
                p.style.display = "block";
                p.style.position = "center"
                // var node = document.createTextNode();
                // p.appendChild(node)

                var img = document.createElement("img")
                img.src = sentimento.imagem.caminho;
                img.className = "rounded-circle mb-3 mt-4"
                img.width = "160"
                img.height = "160"
                p.value = sentimento.id

                p.addEventListener('click', e => {
                    geraAnalise(e.target.value)
                })
                
                console.log(img)
                div3.appendChild(img)
                div3.appendChild(p)
                div2.appendChild(div3)
                div.appendChild(div2)
                

                histSelection.appendChild(div)
            })
        })
}


function geraAnalise(id_sentimento_entry)
{
    query =
    `
    query getRandomAnalise($id_sentimento: ID!)
    {
        getRandomAnalise(id_sentimento: $id_sentimento)
        {
            id
            frase
            {
                id
            }
            sentimento
            {
                id
            }
        }
    }
    `

    variaveis = {id_sentimento: id_sentimento_entry}

    queryFetch(query, variaveis).then(data => {
        if(!data.data.getRandomAnalise)
        {
            const sent = document.getElementById("erro")
            const obrigado_msg = document.createElement("h3")
            let node = document.createTextNode("Sem frases com este sentimento - Cadastre ao menos uma!")
            obrigado_msg.appendChild(node)

            sent.appendChild(obrigado_msg)
            
        }
        else
        {
            addAnalise(data.data.getRandomAnalise.frase.id, 
                data.data.getRandomAnalise.sentimento.id, localStorage.id, 0)
        }
    })
}

function addAnalise(id_frase_entry, id_sentimento_entry, id_usuario_entry, correto_entry)
{
    const mutation = `
    mutation addAnalise($id_frase: ID!, $id_sentimento: ID!, $id_usuario: ID!, $correto: Boolean){
        addAnalise(id_frase: $id_frase, id_sentimento: $id_sentimento, id_usuario: $id_usuario, correto: $correto)
        {
            id
        }
    }
    `
    const variaveis = {id_frase: id_frase_entry, id_sentimento: id_sentimento_entry, id_usuario: id_usuario_entry, correto: correto_entry}

    queryFetch(mutation, variaveis).then(data =>{
            document.location.href = "correto-incorreto.html?idanalise=" + encodeURIComponent(data.data.addAnalise.id);                   
        })

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