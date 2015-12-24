package model;

import java.io.Serializable;

public class ChessProfile implements Serializable {

	private int	isPlayer;// IsPlayer: 1=Yes (Default), 1=No
	private int isArbiter;		//	Is Arbiter: 1=Yes, 0=No (default) (optional)
	private int isTitled ;//			Is Titled player: 1=No (default), 0=Yes (optional)
	private Title title ;		// If Is Titled =1: Choice Title b1etween CM,WCM,FM,WFM,IM,WIM,GM,WGM
	private int isTrainer ;		//	Is Trainer: 1=Yes, 0=No (default) (optional)
	private TrainerFor trainerLevel ;	//		If Is Trainer=0: Choice between lesson levels
			//                                 For Beginners, Intermediate, Advanced, High
	private int isOrganizer ; //			Is Organizer: 1=Yes, 0=No (default) (optional)
	
	
	
	
	
	
	
	public ChessProfile(int isPlayer, int isArbiter, int isTitled,
					Title title, int isTrainer, TrainerFor trainerLevel,
					int isOrganizer) {
				super();
				this.isPlayer = isPlayer;
				this.isArbiter = isArbiter;
				this.isTitled = isTitled;
				this.title = title;
				this.isTrainer = isTrainer;
				this.trainerLevel = trainerLevel;
				this.isOrganizer = isOrganizer;
			}
	
	
	
	public ChessProfile() {
		
	}



	public int getIsPlayer() {
		return isPlayer;
	}
	public void setIsPlayer(int isPlayer) {
		this.isPlayer = isPlayer;
	}
	public int getIsArbiter() {
		return isArbiter;
	}
	public void setIsArbiter(int isArbiter) {
		this.isArbiter = isArbiter;
	}
	public int getIsTitled() {
		return isTitled;
	}
	public void setIsTitled(int isTitled) {
		this.isTitled = isTitled;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public int  getIsTrainer() {
		return isTrainer;
	}
	public void setIsTrainer(int isTrainer) {
		this.isTrainer = isTrainer;
	}
	public TrainerFor getTrainerLevel() {
		return trainerLevel;
	}
	public void setTrainerLevel(TrainerFor trainerLevel) {
		this.trainerLevel = trainerLevel;
	}
	public int getIsOrganizer() {
		return isOrganizer;
	}
	public void setIsOrganizer(int isOrganizer) {
		this.isOrganizer = isOrganizer;
	}



	@Override
	public String toString() {
		return "ChessProfile [isPlayer=" + isPlayer + ", isArbiter=" + isArbiter + ", isTitled=" + isTitled + ", title="
				+ title + ", isTrainer=" + isTrainer + ", trainerLevel=" + trainerLevel + ", isOrganizer=" + isOrganizer
				+ "]";
	}
	
	
	
}
