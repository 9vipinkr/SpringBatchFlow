package com.example.demo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//flow configuration
@Configuration
public class FlowConfiguration {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
		.tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("First step from flow");
				return RepeatStatus.FINISHED;
			}
			
		}).build();
	}
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
		.tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("second step from flow");
				return RepeatStatus.FINISHED;
			}
			
		}).build();
	}

	@Bean
	public Flow flowsample() {
		FlowBuilder<Flow> flowBuilder=new FlowBuilder<Flow>("flowsample");
		 flowBuilder
				.start(step1())
				.next(step2())
				.end();
		
		 return	flowBuilder.build();
	}
}
