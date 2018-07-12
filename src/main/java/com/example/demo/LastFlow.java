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
public class LastFlow {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step myStepLastFlow() {
		return stepBuilderFactory.get("myStepLastFlow")
		.tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("my step from LastFlow");
				return RepeatStatus.FINISHED;
			}
			
		}).build();
	}
	
	@Bean
	public Job lastFlowJob(@Qualifier("flowsample") Flow flowsample) {
		return jobBuilderFactory.get("lastFlowJob") 
				.start(myStepLastFlow())
			    .on("COMPLETED").to(flowsample)
			    .end()				
			    .build();
	}
	
	@Bean
<<<<<<< Upstream, based on branch 'master' of https://github.com/9vipinkr/SpringBatchFlow.git
	public Job lastFlowJob2(@Qualifier("flowsample") Flow flowsample) {
		return jobBuilderFactory.get("lastFlowJob2") 
=======
	public Job lastFlowJob1(@Qualifier("flowsample") Flow flowsample) {
		return jobBuilderFactory.get("lastFlowJob1") 
>>>>>>> 59ec986 Add another job local
				.start(myStepLastFlow())
			    .on("COMPLETED").to(flowsample)
			    .end()				
			    .build();
	}
}
