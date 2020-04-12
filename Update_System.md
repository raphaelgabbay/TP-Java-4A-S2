# Mise à jour du système

**Comment faire pour éviter la coupure de service? Que faire des parties en cours?**

Si une mise à jour est en cours, les partise en cours resteront jouables, en revanche, les nouveaux joueurs seront redirigés vers un conteneur avec la nouvelle version à l'intérieur. Les “vieux” conteneurs n’accepteront plus de nouveaux joueurs et se détruiront quand toutes les parties seront terminées.

**Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétro-compatibilité)**

En utilisant un système de version d’api. (exemple: api/v1 api/v2) Les joueurs du vieux client pourront toujours utilisés v1 pour communiquer avec le système.

**Comment avertir les joueurs de la nouveauté une unique fois ?**

Une bannière apparaissant à la connexion est affiché. On peut imaginer un paramètre en base de donnée pour savoir si le joueur a été notifié de la nouveauté ou non.
