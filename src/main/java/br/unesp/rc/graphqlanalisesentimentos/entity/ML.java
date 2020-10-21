package br.unesp.rc.graphqlanalisesentimentos.entity;

import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.experiment.InstanceQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class ML {

    String model = "/home/maicondallagnol/Documentos/Faculdade/2020/SOO/AnaliseDeSentimentos/src/main/resources/models/classificador.model";

    private Instances queryBaseForWeka() throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL("jdbc:mysql://localhost:3306/projeto_soo");
        query.setUsername("maicondallagnol");
        query.setPassword("17051998");
        query.setQuery("select frase.texto, sentimento.nome from analise inner join frase on " +
                "analise.id_frase=frase.id inner join sentimento on analise.id_sentimento=sentimento.id where correto=1");

        Instances data = query.retrieveInstances();
        data.setClassIndex(data.numAttributes() - 1);

        return data;
    }


    public void makeNewModel() throws Exception {

        Instances data = queryBaseForWeka();
        NaiveBayes classificador = new NaiveBayes();

        classificador.buildClassifier(data);

        SerializationHelper.write(model, classificador);

    }

    private DenseInstance make_instance(String frase_to_classify, SentimentoRepository sentimentoRepository) {
        ArrayList<Attribute> attributeList = createAttributeList(sentimentoRepository);

        Instances data = new Instances("NovaInstancia", attributeList, 0);

        DenseInstance inst_co = new DenseInstance(data.numAttributes());
        data.add(inst_co);
        data.setClassIndex(data.numAttributes() - 1);

        inst_co.setDataset(data);
        inst_co.setValue(attributeList.get(0), frase_to_classify);
        inst_co.setClassMissing();

        return inst_co;
    }

    public Integer make_classification(String frase, SentimentoRepository sentimentoReposit) throws Exception {

        NaiveBayes modelo = load_model();
        DenseInstance instance_frase = make_instance(frase, sentimentoReposit);
        double predicao = modelo.classifyInstance(instance_frase);
        String classe = instance_frase.classAttribute().value((int) predicao);

        return sentimentoReposit.findIdByNome(classe);
    }


    public ArrayList<Attribute> createAttributeList(SentimentoRepository sentimentsRepository)
    {
        ArrayList<Attribute> attributeList = new ArrayList<>();
        List<Sentimento> sentimentos = sentimentsRepository.findAll();
        ArrayList<String> classVal = new ArrayList<>();

        for (Sentimento sentimento : sentimentos) {
            classVal.add(sentimento.getNome());
        }

        Attribute frase = new Attribute("frase", (List<String>) null);
        Attribute classes = new Attribute("sentimento", classVal);

        attributeList.add(frase);
        attributeList.add(classes);

        return attributeList;
    }

    private NaiveBayes load_model() throws Exception {
        return (NaiveBayes) SerializationHelper.read(model);
    }
}
