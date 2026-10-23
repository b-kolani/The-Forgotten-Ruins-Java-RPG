# Les Ruines Oubliées — Cahier des charges

**Document :** `docs/REQUIREMENTS.md`  
**Version :** 1.0 — spécification initiale du MVP  
**Statut :** prêt pour revue avant la conception technique

## 1. Présentation du projet

*Les Ruines Oubliées* est un jeu de rôle textuel (RPG) développé en Java et jouable dans la console. Le joueur explore un ancien donjon, résout des énigmes, évite des pièges, combat des monstres et collecte des objets afin d'atteindre le sanctuaire final.

Le projet vise deux objectifs :

- apprendre et pratiquer Java, la programmation orientée objet, les collections, la gestion des états et les tests automatisés ;
- produire un projet compréhensible, testable et présentable sur GitHub et dans un portfolio.

## 2. Périmètre du MVP

### 2.1 Fonctionnalités incluses

- lancement d'une nouvelle partie ;
- exploration de salles à l'aide de menus textuels ;
- portes ouvertes et portes verrouillées ;
- énigmes de plusieurs types, ajoutées progressivement ;
- trois tentatives par cycle d'énigme ;
- indices gratuits, avec récompense réduite si l'énigme est résolue après utilisation d'un indice ;
- pièges infligeant des dégâts directs ;
- combats au tour par tour ;
- points de vie, potions, clés et objets de quête ;
- progression en difficulté à travers plusieurs zones ;
- victoire à l'objectif final et défaite lorsque les points de vie atteignent zéro ;
- possibilité de commencer une nouvelle partie après la fin de la précédente ;
- tests automatisés des règles principales.

### 2.2 Hors périmètre du MVP

Les éléments suivants sont reportés à une version ultérieure :

- interface graphique ;
- sauvegarde et chargement de partie ;
- carte libre en grille ;
- système avancé d'équipement, d'expérience et de niveaux ;
- génération procédurale du donjon ;
- multijoueur ;
- système de combat en temps réel.

## 3. Parcours de jeu proposé

La première aventure comportera cinq salles principales :

1. **L'entrée oubliée** : découverte des commandes et récupération possible d'une potion.
2. **La porte des anciens** : première devinette et apprentissage du système d'indices.
3. **La crypte** : premier combat et découverte d'un coffre.
4. **Le mécanisme des runes** : puzzle logique ou code à déduire.
5. **Le sanctuaire final** : épreuve finale et condition de victoire.

Ce parcours est le scénario initial. Les salles et leur contenu pourront être ajustés pendant les tests, sans modifier les règles générales du jeu.

## 4. Exploration et déplacements

- Le donjon est composé de salles reliées entre elles.
- Dans chaque salle, le jeu affiche une description et les sorties ou actions disponibles.
- Le joueur choisit une action par son numéro ou par une commande textuelle clairement indiquée.
- Une porte verrouillée ne peut pas être franchie tant que sa condition d'ouverture n'est pas satisfaite.
- Les actions impossibles dans le contexte actuel sont refusées avec un message explicatif.
- Une saisie invalide ne doit ni faire planter le jeu ni consommer une ressource.
- Le joueur peut examiner la salle, consulter son état et ouvrir son inventaire lorsque ces actions sont disponibles.

Le déplacement se fait par choix textuels entre les sorties disponibles, plutôt que par déplacement libre sur une grille.

## 5. Joueur et points de vie

Valeurs initiales proposées pour équilibrer le MVP :

- points de vie au départ : 100 ;
- points de vie maximum : 100 ;
- dégâts d'une attaque normale : 15 ;
- soin d'une potion : 25 ;
- dégâts d'un piège facile : 10 ;
- dégâts d'un piège intermédiaire : 20 ;
- dégâts d'un piège difficile : 30.

Ces valeurs sont des paramètres de départ qui pourront être ajustés après les tests de jeu.

Règles :

- les points de vie ne dépassent jamais le maximum ;
- les points de vie ne descendent jamais en dessous de zéro ;
- subir des dégâts réduit les points de vie, sans résultat négatif ;
- si les points de vie atteignent zéro, le joueur est vaincu et la partie se termine ;
- aucune action de jeu ne peut être effectuée après la fin de la partie, sauf consulter le résultat ou lancer une nouvelle partie.

## 6. Énigmes, tentatives et indices

### 6.1 Types prévus

Les types d'énigmes seront introduits progressivement :

1. **Devinette** : réponse textuelle ou choix parmi plusieurs réponses.
2. **Code secret** : combinaison numérique ou symbolique déduite à partir d'indices.
3. **Mécanisme logique** : ordre de symboles, leviers ou étapes à résoudre.

Le MVP peut commencer avec des devinettes à choix multiple, puis ajouter la saisie de texte et les autres mécanismes.

### 6.2 Tentatives

- Chaque cycle de résolution commence avec trois tentatives.
- Une réponse incorrecte consomme une tentative.
- Une saisie invalide ou une commande inconnue ne consomme pas de tentative.
- Une réponse correcte ouvre la porte ou résout le mécanisme associé.
- Après la troisième mauvaise réponse, le piège de l'énigme se déclenche une fois pour ce cycle.
- Après le piège, si le joueur est encore vivant, l'énigme reste accessible avec trois nouvelles tentatives.
- Le piège ne doit pas se déclencher plusieurs fois pour une même série de trois tentatives.
- Si le piège réduit les points de vie à zéro, la partie se termine immédiatement et aucune nouvelle tentative n'est proposée.
- Une porte déjà ouverte ne demande plus de résoudre son énigme.

### 6.3 Indices et récompenses

- Le joueur peut demander un indice sans consommer de tentative.
- L'utilisation d'au moins un indice marque l'énigme comme « résolue avec indice » pour le cycle courant.
- Si le joueur réussit sans indice, il reçoit la récompense normale prévue.
- S'il réussit après avoir utilisé un indice, il reçoit une récompense réduite.
- La pénalité de récompense ne s'applique qu'à l'énigme concernée, pas aux récompenses futures.
- Le barème exact des récompenses sera défini lorsque le système de récompenses sera conçu.
- Si le joueur échoue puis recommence l'énigme, l'utilisation d'un indice reste prise en compte pour cette énigme jusqu'à sa résolution. Cette règle évite de contourner la pénalité en provoquant volontairement un piège.

### 6.4 Réinitialisation d'un cycle d'énigme

Après le déclenchement du piège, si le joueur survit :

- la porte reste verrouillée ;
- le joueur reçoit trois nouvelles tentatives ;
- le piège du cycle précédent est considéré comme déclenché et ne peut pas se répéter pour ce même cycle ;
- l'énigme reste résoluble ;
- l'état « indice utilisé » reste conservé pour l'énigme afin que la récompense réduite ne puisse pas être contournée.

Une réussite ouvre définitivement la porte pour la partie en cours.

## 7. Pièges

- Les pièges du MVP infligent directement des dégâts au joueur.
- Les dégâts dépendent du niveau de difficulté de la salle ou de l'énigme.
- Le piège est déclenché après trois réponses incorrectes dans un cycle.
- Le déclenchement et l'application des dégâts sont deux effets d'une seule activation : le même piège ne peut pas infliger plusieurs fois les mêmes dégâts à cause d'une seule activation.
- Si le joueur survit, il peut recommencer l'énigme selon les règles de la section 6.
- Si le joueur meurt, la partie se termine immédiatement.

Les pièges qui font apparaître un monstre, consomment un objet ou modifient les passages sont hors périmètre du MVP.

## 8. Combats au tour par tour

Pendant un combat, le joueur peut choisir parmi les actions autorisées :

- **Attaquer** : inflige les dégâts prévus à l'ennemi.
- **Se défendre** : réduit les dégâts de la prochaine attaque ennemie, selon une règle qui sera définie pendant la conception du combat.
- **Utiliser une potion** : consomme une potion et restaure des points de vie, sans dépasser le maximum.
- **Tenter de fuir** : la règle de réussite ou d'échec de la fuite sera précisée lors de la conception du combat.

Ordre général d'un tour :

1. le joueur choisit une action valide ;
2. l'action du joueur est appliquée ;
3. si l'ennemi est vaincu, le combat se termine sans riposte de cet ennemi ;
4. sinon, l'ennemi attaque, en tenant compte d'une éventuelle défense ;
5. si les points de vie du joueur atteignent zéro, la partie se termine.

Une saisie invalide ne fait pas avancer le tour et ne permet pas au monstre d'attaquer gratuitement.

Les valeurs de dégâts des monstres, les règles exactes de défense et de fuite ainsi que les récompenses de combat seront définies avant l'implémentation du système de combat.

## 9. Inventaire et objets

L'inventaire du MVP n'a pas de limite de capacité.

Types d'objets prévus :

- **Potion de soin** : restaure 25 points de vie, dans la limite du maximum ;
- **Clé** : permet d'ouvrir une porte qui exige cette clé ;
- **Objet de quête** : sert à progresser dans une énigme ou à débloquer un événement.

Règles :

- une potion n'est consommée que si son utilisation est valide ;
- une potion ne peut pas être utilisée si le joueur n'en possède aucune ;
- le jeu indique clairement si une potion ne soigne aucun point de vie parce que le joueur est déjà au maximum ; la décision de consommer ou non une potion dans ce cas devra être fixée lors de la conception de l'inventaire ;
- une clé ne peut pas ouvrir une porte qui ne lui correspond pas ;
- une clé consommable est retirée de l'inventaire après une ouverture réussie ; si une clé doit être réutilisable, cette propriété devra être indiquée explicitement ;
- les objets de quête ne peuvent être utilisés que dans les situations qui les acceptent.

## 10. Monstres et difficulté progressive

Le donjon est divisé en zones de difficulté croissante :

- **Zone facile** : devinettes simples, dégâts de pièges faibles et monstres peu résistants ;
- **Zone intermédiaire** : codes à déduire, dégâts plus élevés et ennemis plus résistants ;
- **Zone difficile** : puzzles en plusieurs étapes et épreuves finales.

La difficulté est déterminée par la zone et par les caractéristiques définies pour chaque rencontre, plutôt que par une augmentation aléatoire et invisible.

Les caractéristiques exactes des monstres et les récompenses associées seront définies lors de la conception détaillée des combats.

## 11. Victoire, défaite et nouvelle partie

- La victoire est obtenue lorsque le joueur remplit l'objectif du sanctuaire final.
- La défaite survient lorsque les points de vie du joueur atteignent zéro.
- À la fin de la partie, le jeu affiche clairement le résultat : victoire ou défaite.
- Le joueur peut lancer une nouvelle partie après la fin de l'aventure.
- La nouvelle partie réinitialise les points de vie, l'inventaire, les salles, les portes, les énigmes et l'état des combats.
- Le MVP ne conserve pas la progression après fermeture du programme ; la sauvegarde sera ajoutée ultérieurement.

## 12. Robustesse et entrées utilisateur

- Toute saisie utilisateur doit être validée.
- Un numéro de menu hors limites affiche un message d'erreur et redemande une entrée.
- Une entrée vide ou inconnue ne doit pas faire planter le programme.
- Les commandes indisponibles dans une salle ou pendant un combat sont refusées sans effet secondaire.
- Les valeurs de points de vie et de tentatives sont toujours maintenues dans leurs limites valides.
- Les règles du jeu doivent être séparées autant que possible de l'affichage et de la lecture clavier afin de pouvoir être testées sans interaction console.

## 13. Critères d'acceptation

Le MVP sera considéré comme fonctionnel lorsque les vérifications suivantes réussiront :

1. Une nouvelle partie commence avec 100 points de vie et un inventaire vide, sauf objets de départ explicitement prévus.
2. Le joueur peut consulter les actions disponibles et se déplacer vers une sortie accessible.
3. Une porte verrouillée ne peut pas être franchie avant sa résolution.
4. Une réponse incorrecte réduit le nombre de tentatives de trois à deux, puis à une, puis déclenche le piège.
5. Un piège applique ses dégâts une seule fois par cycle de trois tentatives.
6. Si le joueur survit au piège, l'énigme recommence avec trois tentatives et la porte reste verrouillée.
7. Si le piège tue le joueur, la partie se termine sans nouvelle tentative.
8. Une réponse correcte ouvre la porte et empêche de résoudre à nouveau la même énigme.
9. L'utilisation d'un indice n'enlève pas de tentative, mais réduit la récompense de cette énigme.
10. Une attaque qui vainc un monstre met fin au combat sans riposte de celui-ci.
11. Une potion soigne dans la limite des points de vie maximum et ne peut pas être utilisée si l'inventaire n'en contient aucune.
12. Une saisie de menu invalide ne fait pas planter le programme et ne consomme pas de ressource.
13. Les points de vie ne dépassent jamais 100 et ne deviennent jamais négatifs.
14. La victoire et la défaite empêchent les actions normales de continuer.
15. Une nouvelle partie réinitialise correctement l'état de la partie précédente.

## 14. Points à préciser pendant la conception

Les besoins fonctionnels sont définis, mais certaines règles détaillées restent volontairement ouvertes :

- montant exact des récompenses et réduction appliquée après un indice ;
- effet précis de la défense et règles de fuite ;
- dégâts et points de vie de chaque monstre ;
- comportement d'une potion utilisée alors que les points de vie sont déjà au maximum ;
- liste exacte des objets de quête et règles de consommation des clés ;
- contenu détaillé des cinq salles.

Ces points seront décidés avant d'implémenter la fonctionnalité correspondante. Ils ne bloquent pas la conception générale du jeu, mais devront être réglés avant les tests d'acceptation finaux.
