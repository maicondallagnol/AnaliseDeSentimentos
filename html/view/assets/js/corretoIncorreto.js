var id_analise_entry

window.onload = function () {
    var url = document.location.href,
        params = url.split('?')[1].split('&'),
        data = {}, tmp;
    for (var i = 0, l = params.length; i < l; i++) {
         tmp = params[i].split('=');
         data[tmp[0]] = tmp[1];
    }
    id_analise_entry = data.idanalise;
    getEmoticonAnalise(id_analise_entry)
    getFraseAnalise()
}


function setCorretoIncorreto(correto_entry)
{

    const mutation = `
    mutation updateCorreto($id_analise: ID!, $correto: Boolean!){
        updateCorreto(id_analise: $id_analise, correto: $correto)
      }
    `

    const variaveis = {id_analise: id_analise_entry, correto: correto_entry}

    queryFetch(mutation, variaveis).then(data =>{
        document.location.href = "index.html"
    })
}

function getFraseAnalise()
{
    query = 
    `
    query getAnalise($id_analise: ID!)
    {
        getAnalise(id_analise: $id_analise)
        {
            frase
            {
                texto
            }
            
        }
    }
    `
    variaveis = {id_analise: id_analise_entry}
    queryFetch(query, variaveis).then(data => {
        const frase = document.getElementById("frase")

        const p = document.createElement("p")
        const node = document.createTextNode(data.data.getAnalise.frase.texto)
        p.appendChild(node)
        p.style.fontSize = "20px"
        frase.appendChild(p)
    })

}

function getEmoticonAnalise()
{
    query = 
    `
    query getAnalise($id_analise: ID!)
    {
        getAnalise(id_analise: $id_analise)
        {
            sentimento
            {
                nome
                emoticon
            }
            
        }
    }
    `

    variaveis = {id_analise: id_analise_entry}

    queryFetch(query, variaveis).then(data => {
        const box = document.getElementById("box-correto-incorreto")
        
        const p = document.createElement("p")
        const node = document.createTextNode(data.data.getAnalise.sentimento.nome)
        p.appendChild(node)
        p.style.fontSize = "35px"

        const img = document.createElement("img")
        console.log(data.data.getAnalise.sentimento.emoticon)
        img.src = data.data.getAnalise.sentimento.emoticon
        img.className = "rounded-circle mb-3 mt-4"
        img.width="160"
        img.height="160"
    
        box.insertBefore(p, box.childNodes[0])
        box.insertBefore(img,box.childNodes[0])
        
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