package com.jbjohn.spring.batch.elasticsearch;

import com.jbjohn.spring.objects.SearchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

/**
 */
public class ElasticsearchItemProcessor implements ItemProcessor<SearchRecord, IndexQuery> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchItemProcessor.class);

    @Override
    public IndexQuery process(SearchRecord record) throws Exception {

        IndexQueryBuilder builder = new IndexQueryBuilder();
        builder.withObject(record);
        LOGGER.info("processing search record : " + record.toString() + ", Query : " + builder.toString());
        // use other methods on builder as required

        return builder.build();
    }
}
