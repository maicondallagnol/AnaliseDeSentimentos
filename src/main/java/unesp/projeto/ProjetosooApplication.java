package unesp.projeto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.converters.DatabaseConnection;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.classifiers.bayes.NaiveBayes;

import java.io.File;
import java.util.Properties;
import unesp.projeto.entity.Frase;
import unesp.projeto.service.FraseService;

@SpringBootApplication
public class ProjetosooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetosooApplication.class, args);

		FraseService fs = new FraseService();

//		System.out.println(fs.findByidFrase(1730));

	}


//public static void main(String[] args) throws Exception {
//
//    InstanceQuery query = new InstanceQuery();
//    query.setDatabaseURL("jdbc:mysql://localhost:3306/SOO_PROJETO");
//    query.setUsername("maicondallagnol");
//    query.setPassword("17051998");
//    query.setQuery("select * from imdb");
//
//    Instances data = query.retrieveInstances();
//    data.setClassIndex(data.numAttributes() - 1);




//    NaiveBayes classificador = new NaiveBayes();
//
//    classificador.buildClassifier(data);
//
//    System.out.println("Antes: " + data.instance(1520));
//    data.instance(0).setClassMissing();
//
//    System.out.println("Mudei: " + data.instance(0));
//    double result = classificador.classifyInstance(data.instance(1));
//
//    System.out.println("Classificou: "+ data.instance(0).classAttribute().value((int) result));
//}
}
