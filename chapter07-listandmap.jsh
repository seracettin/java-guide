// To starts, run jshell --enable-preview which is a program able to interpret Java syntax
// then cut and paste the following lines to see how it works
// To exit jshell type /exit

// In Java, the most used data structures are List (and indexed list) and Map (a dictionary)

// To create a simple list
var numbers = List.of(1, 2, 3);

// a list is an indexed data structure that stores object in the order of insertions
// get() access to an element given an index
var firstElement = numbers.get(0);
var lastElement = numbers.get(numbers.size() - 1);

// contains return true if a value is contained in the list
System.out.println(numbers.contains(4));

// indexOf returns the first index of the element in the list
System.out.println(numbers.indexOf(2));

// to loop over the elements of a list, we have a special syntax using the keyword 'for'
var countries = List.of("UK", "US", "France");
for(var country: countries) {
  System.out.println(country);
}

// you can also loop over the elements using a method forEach
// if you don't understand this one, don't panic, we will see it later
countries.forEach(country -> System.out.println(country));


// a list also defines the method equals() and toString(), so
// you can print a list or test if two list are equals
System.out.println(countries);
System.out.println(numbers.equals(countries));


// in Java, depending on how you create a data structure it can be changed
// after creation or not. Implementation that allow mutation after creation
// are called modifiable

// by example, the list above (created with the static method of()) is not modifiable
//countries.set(0, 'Poland')  // throws an UnsupportedOperationException

// To create a modifiable list, we use an ArrayList, created using the operator 'new'
// and because because there is no element in the list, the compiler has no way to know
// the type of the elements so we have to provide it in between angle brackets ('<' and '>')
var modifiableCountries = new ArrayList<String>();

// To add elements in a list, we have the method add()
modifiableCountries.add("UK");
modifiableCountries.add("US");
modifiableCountries.add("France");
modifiableCountries.add("Poland");

// to remove an element, we have the method remove()
modifiableCountries.remove("UK");

// an unmodifiable list or a modifiable list have the same set of methods,
// so you can loop over the modiable list the same way
for(var country: modifiableCountries) {
  System.out.println(country);
}

// To can create an unmodifiable list from a modifiable one with List.copyOf()
var unmodifiableList = List.copyOf(modifiableCountries);
System.out.println(unmodifiableList);

// To create a modifiable list from an unmodifiable one using ArrayList(List)
// In that case you don't have to specify the type of the elements
// the compiler already knows the type of list hence the <> (diamond)
var modifiableList = new ArrayList<>(unmodifiableList);
System.out.println(modifiableList);



// A Map associate a value to a key
// To create a simple Map
var petCost = Map.of("cat", 200, "dog", 350, "lion", 5000);

// to get the value from a key
var costOfADog = petCost.get("dog");
System.out.println(costOfADog);

// warning! warning! asking for a key which is not in the map will return null
var costOfAGirafe = petCost.get("girafe");
System.out.println(costOfAGirafe);

// to avoid null, use the method getOrDefault() that specify a default value
var costOfAGirafe = petCost.getOrDefault("girafe", 0);
System.out.println(costOfAGirafe);

// you can not to a for loop directly on a Map
// but you can do it on the set of keys
for(var pet: petCost.keySet()) {
  System.out.println(pet);
}

// or on the set of couples key/value
for(var entry: petCost.entrySet()) {
  var pet = entry.getKey();
  var cost = entry.getValue();
  System.out.println(pet + " " + cost);
}

// You can also loop over the entries using the method forEach
petCost.forEach((pet, cost) -> {
  System.out.println(pet + " " + cost);
});

// And like a list, a map defines the method equals()/hashCode() and toString()
var lighter = Map.of("blue", "lightblue", "gray", "white");
var darker = Map.of("blue", "darkblue", "gray", "black");
System.out.println(lighter);
System.out.println(darker);
System.out.println(lighter.equals(darker));

// The Map create by Map.of() are non modifiable
// To create a modifiable map, we use new HashMap to create the map
// and map.put to put key/value in it
var modifiableMap = new HashMap<String, String>();
modifiableMap.put("blue", "lightblue");
modifiableMap.put("gray", "white");
System.out.println(modifiableMap);

// Removing the key, remove the couple key/value
modifiableMap.remove("blue");
System.out.println(modifiableMap);

// To make the Map acts as a cache, use computeIfAbsent
record Person(String name, int age) { }
var persons = List.of(new Person("Bob", 23), new Person("anna", 32), new Person("Bob", 12));
var group = new HashMap<String, List<Person>>();
persons.forEach(person -> group.computeIfAbsent(person.name(), name -> new ArrayList<>())
                                .add(person));
System.out.println(group);

// to count the number of occurences, use merge
var letters = List.of("a", "b", "e", "b");
var occurenceMap = new HashMap<String, Integer>();
letters.forEach(letter -> occurenceMap.merge(letter, 1, Integer::sum));
System.out.println(occurenceMap);

// To create a unmodifiableMap from a modifiable map
var unmodifiableMap = Map.copyOf(modifiableMap);
System.out.println(unmodifiableMap);

// To create a modifiableMap from an unmodifiable map
var modifiableMap = new HashMap<>(unmodifiableMap);
System.out.println(modifiableMap);

// To do more transformations of lists and maps, the Stream API is richer
