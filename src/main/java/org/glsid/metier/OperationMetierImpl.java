package org.glsid.metier;

import java.util.Date;

import org.glsid.dao.CompteRepository;
import org.glsid.dao.EmployeRepository;
import org.glsid.dao.OperationRepository;
import org.glsid.entities.Compte;
import org.glsid.entities.Employe;
import org.glsid.entities.Operation;
import org.glsid.entities.Retrait;
import org.glsid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationMetierImpl implements OperationMetier {
	
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	CompteRepository compteRepository;
    @Autowired
    EmployeRepository employeRepository;
	
	@Override
	@Transactional
	public boolean verser(String code, double montant, Long codeEmp) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findById(code).orElse(null);
		Employe e=employeRepository.findById(codeEmp).orElse(null);
		Operation o = new Versement();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()+montant);
		return true;
	}

	@Override
	@Transactional
	public boolean retirer(String code, double montant, Long codeEmp) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findById(code).orElse(null);
		
		if(cp.getSolde()<montant) { 
			throw new BigValueException("solde insuffisant");
		}
		Employe e=employeRepository.findById(codeEmp).orElse(null);
		Operation o = new Retrait();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()-montant);
		return true;
	}

	@Override
	@Transactional
	public boolean virement(String cpte1, String cpte2, double montant, Long codeEmp) {
		// TODO Auto-generated method stub
		retirer(cpte1, montant, codeEmp);
		verser(cpte2, montant, codeEmp);
		return true;
	}

	@Override
	public PageOperation getOperation(String codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		Page<Operation> ops = operationRepository.getOperations(codeCompte, PageRequest.of(page, size));
		PageOperation pOp = new PageOperation();
		pOp.setOperations(ops.getContent());
		pOp.setNombreOperations(ops.getNumberOfElements());
		pOp.setPage(ops.getNumber());
		pOp.setTotalPages(ops.getTotalPages());
		pOp.setTotalOperations((int)ops.getTotalElements());
		return pOp;
	}

}