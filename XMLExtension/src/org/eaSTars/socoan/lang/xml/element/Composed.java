package org.eaSTars.socoan.lang.xml.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.Fragment;

public abstract class Composed extends XmlElementFragment implements Iterable<Fragment> {

	private Optional<Fragment> startingComponent = Optional.empty();
	
	private List<Fragment> components = new ArrayList<Fragment>();

	private Optional<Fragment> endingComponent = Optional.empty();
	
	public Composed(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public Optional<Fragment> getStartingComponent() {
		return startingComponent;
	}

	public void setStartingComponent(Fragment startingComponent) {
		this.startingComponent = Optional.ofNullable(startingComponent);
	}
	
	@Override
	public Iterator<Fragment> iterator() {
		return components.iterator();
	}
	
	@Override
	public void forEach(Consumer<? super Fragment> action) {
		components.forEach(action);
	}
	
	public int getComponentsize() {
		return components.size();
	}

	public boolean addComponent(Fragment e) {
		return components.add(e);
	}

	public Fragment getComponent(int index) {
		return components.get(index);
	}

	public List<Fragment> getComponents() {
		return components;
	}

	public void setComponents(List<Fragment> components) {
		this.components = components;
	}

	public Optional<Fragment> getEndingComponent() {
		return endingComponent;
	}

	public void setEndingComponent(Fragment endingComponent) {
		this.endingComponent = Optional.ofNullable(endingComponent);
	}

}
