package br.com.senac.inicializacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Aluno;
import br.com.senac.domain.Professor;
import br.com.senac.service.AlunoService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService professorService;
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("João");
		alunoService.salvar(aluno1);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Maria");
		alunoService.salvar(aluno2);
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("José");
		alunoService.salvar(aluno3);
		
		Professor professor1 = new Professor();
		professor1.setNome("Prof. Marcos");
		professorService.salvar(professor1);
		
		Professor professor2 = new Professor();
		professor2.setNome("Prof. Regina");
		professorService.salvar(professor2);
		
		Professor professor3 = new Professor();
		professor3.setNome("Prof. Maristela");
		professorService.salvar(professor3);
		
	}

}
