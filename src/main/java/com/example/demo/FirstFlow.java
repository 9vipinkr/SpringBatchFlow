package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstFlow {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step myStepFirstFlow() {
		return stepBuilderFactory.get("myStepFirstFlow")
		.tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("my step from firstflow");
				return RepeatStatus.FINISHED;
			}
			
		}).build();
	}
	//reset
	@Bean
	public Job firstFlowJob(@Qualifier("flowsample") Flow flowsample) {
		return jobBuilderFactory.get("firstFlowJob") 
				.start(flowsample)
				.next(myStepFirstFlow())
				.end()				
				.build();
	}
	
}
