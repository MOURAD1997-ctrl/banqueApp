package org.glsid.metier;

import java.util.Date;

import org.glsid.dao.CompteRepository;
import org.glsid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {
	
	@Autowired
	CompteRepository compteRepository;

	@Override
	public Compte saveCompte(Compte cp) {
		cp.setDateCreation(new Date());
		// TODO Auto-generated method stub
		return compteRepository.save(cp);
	}

	@Override
	public Compte getCompte(String code) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findById(code).orElse(null);
        
		if(cp == null) {
			throw new NotFoundException("Compte inexistant!");
		}
		
		return cp;
	}

}