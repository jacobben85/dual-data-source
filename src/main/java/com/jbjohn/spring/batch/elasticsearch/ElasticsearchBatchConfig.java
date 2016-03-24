package com.jbjohn.spring.batch.elasticsearch;

import com.jbjohn.spring.objects.SearchRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Elasticsearch reader writer config
 */
@Configuration
public class ElasticsearchBatchConfig {

    @Bean
    public ElasticsearchItemReader<SearchRecord> elasticsearchItemReader() {
        return new ElasticsearchItemReader<>(elasticsearchOperations(), query(), SearchRecord.class);
    }

    @Bean
    public ElasticsearchItemWriter elasticsearchItemWriter() {
        return new ElasticsearchItemWriter(elasticsearchOperations());
    }

    @Bean
    public ItemProcessor<SearchRecord, IndexQuery> itemProcessor() {
        return new ElasticsearchItemProcessor();
    }

    @Bean
    public SearchQuery query() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // create query as required using the methods on the builder object
        QueryBuilder qb = QueryBuilders.matchAllQuery();

        builder.withIndices("search").withTypes("record");
        builder.withQuery(qb);
        return builder.build();
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }


    public Job updateEmployee(JobBuilderFactory jobs, Step sync) {
        return jobs.get("searchSync")
                .incrementer(new RunIdIncrementer())
                .flow(sync)
                .end()
                .build();
    }

    public Step sync(StepBuilderFactory stepBuilderFactory, ElasticsearchItemReader<SearchRecord> reader,
                      ElasticsearchItemWriter writer, ItemProcessor<SearchRecord, IndexQuery> processor) {
        return stepBuilderFactory.get("sync")
                .<SearchRecord, IndexQuery>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
