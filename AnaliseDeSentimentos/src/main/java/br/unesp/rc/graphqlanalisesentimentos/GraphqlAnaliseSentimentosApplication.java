package br.unesp.rc.graphqlanalisesentimentos;

import br.unesp.rc.graphqlanalisesentimentos.resolver.query.SentimentoQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

@SpringBootApplication
public class GraphqlAnaliseSentimentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlAnaliseSentimentosApplication.class, args);
	}

}
